import React from "react";

// import './App.css';

import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./Component/Login/Login";
import AdminDashboard from "./Component/AdminDashboard/AdminDashboard";
import CreateStudent from "./Component/CreateStudent/CreateStudent";
import CreateCourse from "./Component/createCourse/createCourse";
import AddCourse from "./Component/AddCourse/AddCourse";
import Edit from "./Component/AdminDashboard/Edit";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" index element={<Login />} />

          <Route path="/api/instructor" element={<AdminDashboard />} />
          <Route
            path="/api/instructor/createStudent"
            element={<CreateStudent />}
          />
          <Route
            path="/api/instructor/CreateCourse"
            element={<CreateCourse />}
          />
          <Route path="/api/instructor/AddCourse" element={<AddCourse />} />
          <Route path="/api/instructor/Edit" element={<Edit />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
