import { Footer } from 'components/Footer/Footer';
import { AppHeader } from 'components/Header/Header';
import { NotesPage } from 'containers/NotesPage/NotesPage';
import { LandingPage } from 'containers/LandingPage/LandingPage';
import { LoginPage } from 'containers/LoginPage/Login';
import { NavBar } from 'containers/Navbar/Navbar';
import Protected from 'containers/Protected';
import { AuthProvider } from 'context/authProvider';
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { MediaContextProvider, mediaStyles } from 'styles/AppMedia';

const menuItems = [{ as: 'a', content: 'Notes', key: 'note', href: '/notes' }];
const loginItems = [
  { as: 'a', content: 'Login', key: 'login', href: '/login' },
];
const logoutItems = [
  { as: 'a', content: 'Logout', key: 'logout', href: '/logout' },
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
