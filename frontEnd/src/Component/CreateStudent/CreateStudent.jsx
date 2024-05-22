import React, { useState } from 'react';
import axios from 'axios';
import './CreateStudent.css'; // Import the CSS file
import Navbar from '../navbar/Navbar'; // Correct import statement

export default function CreateStudent() {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  const [errors, setErrors] = useState([]);
  const [success, setSuccess] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (formData.password !== formData.confirmPassword) {
      setErrors(['Passwords do not match']);
      return;
    }

    const newUser = {
      username: formData.name,
      email: formData.email,
      password: formData.password,
      roles: [] // Backend will handle setting the default role
    };

    axios.post("http://localhost:8080/api/login",newUser)
      .then(res=>{
        console.log(res)
      }).catch(res=>{
        console.log(res)
      })

  return (
    <>
      <div className='create'>
        <Navbar />

        <div className='createform'>
          <form className="formRe" onSubmit={handleSubmit}>
            <p className="title">Register</p>
            <p className="message">Signup now and get full access to our app.</p>

            {errors.length > 0 && (
              <ul className="error">
                {errors.map((error, index) => (
                  <li key={index}>{error}</li>
                ))}
              </ul>
            )}
            {success && <p className="success">{success}</p>}

            <label>
              <input
                className="inputrg"
                type="text"
                name="name"
                placeholder=""
                value={formData.name}
                onChange={handleChange}
                required
              />
              <span>Name</span>
            </label>

            <label>
              <input
                className="inputrg"
                type="email"
                name="email"
                placeholder=""
                value={formData.email}
                onChange={handleChange}
                required
              />
              <span>Email</span>
            </label>

            <label>
              <input
                className="inputrg"
                type="password"
                name="password"
                placeholder=""
                value={formData.password}
                onChange={handleChange}
                required
              />
              <span>Password</span>
            </label>

            <label>
              <input
                className="inputrg"
                type="password"
                name="confirmPassword"
                placeholder=""
                value={formData.confirmPassword}
                onChange={handleChange}
                required
              />
              <span>Confirm password</span>
            </label>

            <button className="submit" type="submit">
              Submit
            </button>
            <p className="signin">Already have an account?</p>
          </form>
        </div>
      </div>
    </>
  );
}
}