import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginScreen from "./screens/LoginScreen";
import RegisterScreen from "./screens/RegisterScreen";
import HomeScreen from "./screens/HomeScreen";
import UserProfile from "./screens/UserProfileScreen";
import EditProfile from "./screens/EditProfileScreen";
import Messages from "./screens/MessagesScreen";
import MessageScreen from "./screens/MessagesScreen";
import CreateJobAdvertScreen from "./screens/CreateJobAdvertScreen";
import UserListScreen from "./screens/UserListScreen";
import ListJobOpeningsPage from "./screens/ListJobOpeningsPage";
import ConnectionRequestScreen from "./screens/ConnectionRequestScreen";
import MyApplicationsScreen from "./screens/MyApplicationsScreen";
import MyAdvertsPage from "./screens/MyAdvertsScreen";
import MyAdvertsScreen from "./screens/MyAdvertsScreen";
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
            <Route path="messages" element={<Messages />} />
            <Route path="userlist" element={<UserListScreen />} />
            <Route path="/connection-requests" element={<ConnectionRequestScreen />} />
            <Route path="messages/:selectUserId?" element={<Messages />} />
            <Route path="/jobopenings" element={<ListJobOpeningsPage />} />
            <Route path="/createadvert" element={<CreateJobAdvertScreen />} />
            <Route path="/myApplications/:userId" element={<MyApplicationsScreen />} />
            <Route path="/myAdverts/:userId" element={<MyAdvertsScreen />} />
            {/*<Route path="profile" element={<UserProfile />} />*/}
            {/*<Route path="home" element={<HomeScreen />} />*/}
        </Routes>
      </Router>
  );
}

export default App;
