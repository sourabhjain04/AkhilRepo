const API = '/students';

const els = {
  form: document.getElementById('studentForm'),
  id: document.getElementById('id'),
  name: document.getElementById('name'),
  email: document.getElementById('email'),
  saveBtn: document.getElementById('saveBtn'),
  resetBtn: document.getElementById('resetBtn'),
  table: document.getElementById('studentsTable').querySelector('tbody'),
  rowTpl: document.getElementById('rowTemplate'),
  refreshBtn: document.getElementById('refreshBtn'),
  searchBox: document.getElementById('searchBox')
};

let allStudents = [];

async function listStudents() {
  const res = await fetch(API);
  if (!res.ok) {
    alert('Failed to load students');
    return;
  }
  allStudents = await res.json();
  renderTable(allStudents);
}

function renderTable(rows) {
  els.table.innerHTML = '';
  rows.forEach((s, i) => {
    const clone = els.rowTpl.content.cloneNode(true);
    clone.querySelector('.idx').textContent = i + 1;
    clone.querySelector('.sid').textContent = s.id || '';
    clone.querySelector('.sname').textContent = s.name || '';
    clone.querySelector('.semail').textContent = s.email || '';

    clone.querySelector('.edit').addEventListener('click', () => loadIntoForm(s));
    clone.querySelector('.delete').addEventListener('click', () => deleteStudent(s.id));

    els.table.appendChild(clone);
  });
}

function loadIntoForm(s) {
  els.id.value = s.id || '';
  els.name.value = s.name || '';
  els.email.value = s.email || '';
  els.name.focus();
}

async function createStudent(payload) {
  const res = await fetch(API, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg || 'Create failed');
  }
  return res.json();
}

async function updateStudent(id, payload) {
  const res = await fetch(`${API}/${encodeURIComponent(id)}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg || 'Update failed');
  }
  return res.json();
}

async function deleteStudent(id) {
  if (!id) return;
  if (!confirm('Delete this student?')) return;
  const res = await fetch(`${API}/${encodeURIComponent(id)}`, { method: 'DELETE' });
  if (!res.ok && res.status !== 204) {
    const msg = await res.text();
    alert(msg || 'Delete failed');
    return;
  }
  await listStudents();
  resetForm();
}

function resetForm() {
  els.id.value = '';
  els.form.reset();
  els.name.focus();
}

els.form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const payload = { name: els.name.value.trim(), email: els.email.value.trim() };
  try {
    if (els.id.value) {
      await updateStudent(els.id.value, payload);
    } else {
      await createStudent(payload);
    }
    await listStudents();
    resetForm();
  } catch (err) {
    alert(err.message);
  }
});

els.resetBtn.addEventListener('click', resetForm);
els.refreshBtn.addEventListener('click', listStudents);

els.searchBox.addEventListener('input', (e) => {
  const q = e.target.value.toLowerCase();
  const filtered = allStudents.filter(
    s => (s.name || '').toLowerCase().includes(q) || (s.email || '').toLowerCase().includes(q)
  );
  renderTable(filtered);
});

// initial load
listStudents();
