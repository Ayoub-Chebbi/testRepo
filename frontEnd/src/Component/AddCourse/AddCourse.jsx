import React from 'react';
import './AddCourse.css'; // Import the CSS file
import Navbar from '../navbar/Navbar'; // Correct import statement

export default function AddCourse() {

  return (
    <>
      <div className='create'>
        <Navbar />
        <div className='createform' >
          <form className="formCo">
            <p className="title">Add Course</p>
            <p className="message"></p>

            <label>
  <span className="labelText"></span>
  <select className="inputrg">
  
    <option>Python</option>
    <option>Java</option>
    <option>React</option>
    <option>Html</option>
    <option>css</option>
    <option>c#</option>
  </select>
</label>
            <label>
              <input className="inputrg" type="text" placeholder="" required />
              <span>User email</span>
            </label>


            <button className="submit">Add  Course</button>
          </form>
        </div>
      </div>
    </>
  );
}
