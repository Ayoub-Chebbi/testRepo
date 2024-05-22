import React from 'react';
import './createCourse.css'; // Import the CSS file
import Navbar from '../navbar/Navbar'; // Correct import statement

export default function CreateCourse() {


  return (
    <>
      <div className='create'>
        <Navbar />
        <div className='createform' >
          <form className="formCo">
            <p className="title">Create Course</p>
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
              <span>Course Title</span>
            </label>
            <label>
              <input className="inputrg" type="text" placeholder="" required />
              <span>course Descriptions</span>
            </label>
            <label>
              <input className="inputrg" type="text" placeholder="" required />
              <span>File Upload</span>
            </label>

            <button className="submit">Create new Course</button>
          </form>
        </div>
      </div>
    </>
  );
}
