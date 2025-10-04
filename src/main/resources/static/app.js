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

// API object
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
  },
  async getJobs(){
    return fetch('/job/all');
  },
  async addJob(payload){
    return fetch('/job/post', {
      method:'POST',
      headers:{'Content-Type':'application/json'},
      body: JSON.stringify(payload)
    });
  },
  async getJobsByRecruiter(rid){
    return fetch(`/jobs/recruiter/${rid}`);
  },
  async applyJob(payload){
    return fetch('/application/apply', {
      method:'POST',
      headers:{'Content-Type':'application/json'},
      body: JSON.stringify(payload)
    });
  },
  async getApplicationsByEmployee(eid){
    const res = await fetch(`/application/for-employee/${eid}`);
    if(res.ok) return res.json();
    if(res.status === 204) return [];
    throw new Error(`Failed to fetch applications: ${res.status} ${res.statusText}`);
  },
  async getApplicationsByJob(jid){
    return fetch(`/application/for-job/${jid}`);
  }
};

// ---- Login page wiring ----
const loginForm = qs('#loginForm');
if(loginForm){
  loginForm.addEventListener('submit', async (e)=>{
    e.preventDefault();
    const role = qs('input[name=role]:checked').value;
    const email = qs('#email').value.trim();
    const password = qs('#password').value.trim();
    const apiCall = role === 'employee' ? API.employeeLogin : API.recruiterLogin;
    const res = await apiCall({email, password});
    if(res.ok){
      const data = await res.json().catch(()=>({}));
      const user = { role: role.toUpperCase(), email, name: data.name || '', id: data.employeeId || null };
      auth.set(user);
      alert('Welcome ' + (user.name || user.email));
      // location.href = '/dashboard.html';
      location.href = role === 'employee' ? '/dashboard.html' : '/recruiter-dashboard.html';

    } else {
      alert('Invalid credentials');
    }
  });
}




// Signup Form Wiring
const signupForm = qs('#signupForm');
if(signupForm){
  signupForm.addEventListener('submit', async (e)=>{
    e.preventDefault();

    const role = qs('input[name=role]:checked')?.value;
    const name = qs('#name')?.value?.trim();
    const email = qs('#email')?.value?.trim();
    const password = qs('#password')?.value?.trim();

    const payload = { name, email, password };

    if(role === 'employee'){
      payload.degree = qs('#degree')?.value?.trim();
      payload.passout_year = parseInt(qs('#passout_year')?.value || '0', 10);
      payload.skills = qs('#skills')?.value?.trim();

      const res = await API.employeeSignup(payload);
      if(res.ok){
        alert('Employee registered successfully!');
        location.href = '/login.html';
      } else {
        alert('Employee registration failed.');
      }

    } else if(role === 'recruiter'){
      payload.company = qs('#company')?.value?.trim();
      payload.company_email = qs('#company_email')?.value?.trim();

      const res = await API.recruiterSignup(payload);
      if(res.ok){
        alert('Recruiter registered successfully!');
        location.href = '/login.html';
      } else {
        alert('Recruiter registration failed.');
      }
    } else {
      alert("Please select a role.");
    }
  });
}
