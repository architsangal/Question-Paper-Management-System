import React, { useState } from 'react';
import Swal from 'sweetalert2';
import axios from 'axios';
const Login = ({ setIsAuthenticated, setIsRegistering }) => {
  // const adminEmail = 'admin@example.com';
  // const adminPassword = 'qwerty';

  const [uname, setUname] = useState('admin');
  const [password, setPassword] = useState('admin');

  const handleLogin = e => {
    e.preventDefault();
    let details = {
      username : uname,
      password : password
    }
    // send a post request with details to http://localhost:8090/author/login
    axios.post("http://localhost:8090/author/login",details).then((response) => {
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
      <form onSubmit={handleLogin}>
        <h1>Author Login</h1>
        <label htmlFor="uname">uname</label>
        <input
          id="uname"
          type="text"
          name="uname"
          placeholder="admin@example.com"
          value={uname}
          onChange={e => setUname(e.target.value)}
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
        <input style={{ marginTop: '12px' }} type="submit" value="Login" />
      </form>
      <label for = "register">Don't have an account?</label>
      <input
            className="button"
            type="button"
            value="Register"
            onClick={() => setIsRegistering(true)}
          />
    </div>
  );
};

export default Login;
