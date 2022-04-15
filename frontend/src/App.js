import { Footer } from 'components/Footer/Footer';
import { AppHeader } from 'components/Header/Header';
import { LandingPage } from 'containers/LandingPage/LandingPage';
import { LoginPage } from 'containers/LoginPage/Login';
import { NavBar } from 'containers/Navbar/Navbar';
import { NotesPage } from 'containers/NotesPage/NotesPage';
import Protected from 'containers/Protected';
import { AuthProvider } from 'context/authProvider';
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AuthenticationService from 'services/authService';
import { MediaContextProvider, mediaStyles } from 'styles/AppMedia';

const menuItems = [{ as: 'a', content: 'Notes', key: 'note', href: '/notes' }];
const loginItems = [
  { as: 'a', content: 'Login', key: 'login', href: '/login' },
];

function logoutF() {
  AuthenticationService.logout();
  window.location.href = '/';
}

const logoutItems = [
  {
    as: 'button',
    content: 'Logout',
    key: 'logout',
    onClick: logoutF,
  },
];

const App = () => {
  let user = localStorage.getItem('user');
  user = JSON.parse(user);

  return (
    <>
      <style>{mediaStyles}</style>

      <MediaContextProvider>
        <AuthProvider userData={user}>
          <BrowserRouter>
            <NavBar
              leftItems={menuItems}
              rightItems={
                user && user.token && user.token !== ''
                  ? logoutItems
                  : loginItems
              }
            >
              <AppHeader />

              <Routes>
                <Route path="/" element={<LandingPage />} exact />
                <Route
                  path="/notes"
                  element={
                    <Protected
                      isLoggedIn={user && user.token && user.token !== ''}
                    >
                      <NotesPage />
                    </Protected>
                  }
                />
                <Route path="/login" element={<LoginPage />} />
                <Route path="*" element={<div>No page</div>} />
              </Routes>
            </NavBar>
          </BrowserRouter>
          <Footer />
        </AuthProvider>
      </MediaContextProvider>
    </>
  );
};

export default App;
