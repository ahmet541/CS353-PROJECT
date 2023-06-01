import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginScreen from "./screens/LoginScreen";
import RegisterScreen from "./screens/RegisterScreen";
import HomeScreen from "./screens/HomeScreen";
import UserProfile from "./screens/UserProfileScreen";
import EditProfile from "./screens/EditProfileScreen";
import Messages from "./screens/MessagesScreen";
import MessageScreen from "./screens/MessagesScreen";
import UserProfile from "./screens/UserProfile";
import CreateJobAdvertScreen from "./screens/CreateJobAdvertScreen";
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
            <Route path="/profile/:userId" element={<UserProfile />} />
            <Route path="editProfile" element={<EditProfile />} />
            <Route path="messages/:selectUserId?" element={<Messages />} />

            <Route path="/createadvert" element={<CreateJobAdvertScreen />} />
            {/*<Route path="profile" element={<UserProfile />} />*/}
            {/*<Route path="home" element={<HomeScreen />} />*/}
        </Routes>
      </Router>
  );
}

export default App;
