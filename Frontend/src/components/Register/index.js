import React, { useState } from 'react';
import Swal from 'sweetalert2';
import axios from 'axios';
const Register = ({ setIsAuthenticated, setIsRegistering }) => {
  // const adminEmail = 'admin@example.com';
  // const adminPassword = 'qwerty';

  const [uname, setUname] = useState('admin');
  const [email, setEmail] = useState('admin@example.com');
  const [password, setPassword] = useState('admin');

  const handleRegister = e => {
    e.preventDefault();
    let details = {
      username : uname,
      password : password,
      email: email
    }
    // send a post request with details to http://localhost:8090/author/Register
    axios.post("http://localhost:8090/author/add",details).then((response) => {
      console.log(response);
      if (response.data != null) {
        Swal.fire({
          timer: 1500,
          showConfirmButton: false,
          willOpen: () => {
            Swal.showLoading();
          },
          willClose: () => {
            localStorage.setItem('is_authenticated', true);
            localStorage.setItem('author_id', response.data.id);
            setIsAuthenticated(true);
  
            Swal.fire({
              icon: 'success',
              title: 'Successfully logged in!',
              showConfirmButton: false,
              timer: 1500,
            });
          },
        });
      } else {
        Swal.fire({
          timer: 1500,
          showConfirmButton: false,
          willOpen: () => {
            Swal.showLoading();
          },
          willClose: () => {
            Swal.fire({
              icon: 'error',
              title: 'Error!',
              text: 'Incorrect email or password.',
              showConfirmButton: true,
            });
          },
        });
      }
    });

  };

  return (
    <div className="small-container">
      <form onSubmit={handleRegister}>
        <h1>Author Register</h1>
        <label htmlFor="uname">uname</label>
        <input
          id="uname"
          type="text"
          name="uname"
          placeholder="admin@example.com"
          value={uname}
          onChange={e => setUname(e.target.value)}
        />
        <label htmlFor="email">email</label>
        <input
          id="email"
          type="email"
          name="email"
          placeholder="admin@example.com"
          value={email}
          onChange={e => setEmail(e.target.value)}
        />
        <label htmlFor="password">Password</label>
        <input
          id="password"
          type="password"
          name="password"
          placeholder="qwerty"
          value={password}
          onChange={e => setPassword(e.target.value)}
        />
        <input style={{ marginTop: '12px' }} type="submit" value="Register" />
      </form>
      <label for = "login">Already have an account?</label>
      <input
            className="button"
            type="button"
            value="Login"
            onClick={() => setIsRegistering(false)}
          />
    </div>
  );
};

export default Register;
