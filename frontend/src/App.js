import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginScreen from "./screens/LoginScreen";
import RegisterScreen from "./screens/RegisterScreen";
import HomeScreen from "./screens/HomeScreen";
// Import other screens/components

function App() {
  return (
      <Router>
        <Routes>
          {/*<Route path="/" element={<LoginScreen />} />*/}
            <Route index element={<LoginScreen />} />
            <Route path="login" index element={<LoginScreen />} />
            <Route path="register" element={<RegisterScreen />} />
            <Route path="home" element={<HomeScreen />} />
            {/*<Route path="home" element={<HomeScreen />} />*/}
        </Routes>
      </Router>
  );
}

export default App;
