// Tiny helper
const qs = (s, el=document)=> el.querySelector(s);
const qsa = (s, el=document)=> Array.from(el.querySelectorAll(s));

// State (localStorage)
const auth = {
  set(user){ localStorage.setItem('gh_user', JSON.stringify(user)); },
  get(){ try{ return JSON.parse(localStorage.getItem('gh_user')) }catch{ return null } },
  clear(){ localStorage.removeItem('gh_user'); }
};

// Navbar user indicator
(function initNav(){
  const span = qs('#navUser');
  if(!span) return;
  const u = auth.get();
  span.textContent = u ? (u.role + ': ' + (u.name || u.email)) : 'Guest';
})();

// Home search -> jobs.html
const searchForm = qs('#searchForm');
if(searchForm){
  searchForm.addEventListener('submit', e=>{
    e.preventDefault();
    const q = qs('#q').value.trim();
    const loc = qs('#loc').value.trim();
    const url = new URL('/jobs.html', location.origin);
    if(q) url.searchParams.set('q', q);
    if(loc) url.searchParams.set('loc', loc);
    location.href = url.toString();
  });
}

// ---- API base (same server) ----
// Tumhara backend endpoints:
// POST /employee/add, /employee/login, /recruiter/add, /recruiter/login
const API = {
  async employeeSignup(payload){
    return fetch('/employee/add', {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify(payload)});
  },
  async employeeLogin(payload){
    return fetch('/employee/login', {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify(payload)});
  },
  async recruiterSignup(payload){
    return fetch('/recruiter/add', {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify(payload)});
  },
  async recruiterLogin(payload){
    return fetch('/recruiter/login', {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify(payload)});
  }
};

// ---- Login page wiring ----
const loginForm = qs('#loginForm');
if(loginForm){
  loginForm.addEventListener('submit', async (e)=>{
    e.preventDefault();
    const role = qs('input[name=role]:checked').value; // employee | recruiter
    const email = qs('#email').value.trim();
    const password = qs('#password').value.trim();
    const apiCall = role === 'employee' ? API.employeeLogin : API.recruiterLogin;
    const res = await apiCall({email, password});
    if(res.ok){
      const data = await res.json().catch(()=>({}));
      // Expecting either { id, name, email, ... } OR any ok response
      const user = { role: role.toUpperCase(), email, name: data.name || '', id: data.id || null };
      auth.set(user);
      alert('Welcome ' + (user.name || user.email));
      location.href = '/dashboard.html';
    } else {
      qs('#loginError').style.display='block';
    }
  });
}

//logout

  document.getElementById('logout').addEventListener('click', async function (e) {
    e.preventDefault();

    try {
      // Call backend logout
      await fetch("/logout", {
        method: "POST",
        credentials: "include"
      });
    } catch (err) {
      console.error("Backend logout failed:", err);
    }

    // Clear frontend storage
    localStorage.removeItem("gh_user");

    // Redirect to login
    window.location.href = "/login.html";
  });


// ---- Signup page wiring ----
const signupForm = qs('#signupForm');
if(signupForm){
  signupForm.addEventListener('submit', async (e)=>{
    e.preventDefault();
    const role = qs('input[name=role]:checked').value; // employee | recruiter
    const payload = {};
    // Common fields
    payload.name = qs('#name').value.trim();
    payload.email = qs('#email').value.trim();
    payload.password = qs('#password').value.trim();

    if(role==='employee'){
      payload.degree = qs('#degree').value.trim();
      payload.passout_year = parseInt(qs('#passout_year').value || '0', 10);
      payload.skills = qs('#skills').value.trim();
      const res = await API.employeeSignup(payload);
      alert(res.ok ? 'Employee registered!' : 'Failed to register');
    } else {
      payload.company = qs('#company').value.trim();
      payload.company_email = qs('#company_email').value.trim();
      const res = await API.recruiterSignup(payload);
      alert(res.ok ? 'Recruiter registered!' : 'Failed to register');
    }
  });
}

// ---- Dashboard ----
(function initDashboard(){
  const el = qs('#whoami');
  if(!el) return;
  const u = auth.get();
  if(!u){ el.textContent = 'Not logged in'; return; }
  el.innerHTML = `<span class="badge">${u.role}</span> ${u.name || u.email}`;
  qs('#logout')?.addEventListener('click', ()=>{ auth.clear(); location.href='/login.html'; });
})();

qs('#showProfile')?.addEventListener('click', e=>{
  e.preventDefault();
  loadProfile(); // call the function that fetches from /employee/profile
});




// ----- JOB APIs -----
API.getJobs = async function(){
  const res = await fetch('/job/all');
  return res.json();
};

API.addJob = async function(payload){
  return fetch('/job/post', {
    method:'POST',
    headers:{'Content-Type':'application/json'},
    body: JSON.stringify(payload)
  });
};

API.getJobsByRecruiter = async function(rid){
  const res = await fetch(`/jobs/recruiter/${rid}`);
  return res.json();
};

// ----- APPLICATION APIs -----
API.applyJob = async function(payload){
  return fetch('/application/add', {
    method:'POST',
    headers:{'Content-Type':'application/json'},
    body: JSON.stringify(payload)
  });
};

API.getApplicationsByEmployee = async function(eid){
  const res = await fetch(`/application/employee/${eid}`);
  return res.json();
};

API.getApplicationsByJob = async function(jid){
  const res = await fetch(`/application/job/${jid}`);
  return res.json();
};


// --------- Profile fetching function ----------


 function loadProfile() {
    const profileDiv = document.getElementById('profileSection');
    if (!profileDiv) return;

    fetch('/employee/profile', {
      method: 'GET',
      credentials: 'include'
    })
    .then(response => {
      if (!response.ok) throw new Error("You are not logged in or session expired.");
      return response.json();
    })
    .then(employee => {
      profileDiv.innerHTML = `
        <div class="profile-field-card"><strong>Name:</strong> <span>${employee.name}</span></div>
        <div class="profile-field-card"><strong>Email:</strong> <span>${employee.email}</span></div>
        <div class="profile-field-card"><strong>Degree:</strong> <span>${employee.degree}</span></div>
        <div class="profile-field-card"><strong>Passout Year:</strong> <span>${employee.passout_year}</span></div>
        <div class="profile-field-card"><strong>Skills:</strong> <span>${employee.skills}</span></div>
      `;
    })
    .catch(error => {
      profileDiv.innerHTML = `<p style="color:red;">${error.message}</p>`;
    });
  }

  document.addEventListener('DOMContentLoaded', loadProfile);













