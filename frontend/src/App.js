import { Footer } from 'components/Footer/Footer';
import { AppHeader } from 'components/Header/Header';
import { NavBar } from 'containers/Navbar/Navbar';
import React, { useState } from 'react';
import { MediaContextProvider, mediaStyles } from 'styles/AppMedia';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LoginPage from 'containers/LoginPage/Login';
import { HomePage } from 'containers/HomePage/Home';

const notes = [
  { id: 1, text: 'test', dateCreated: '2022-04-08 14:54:11', edited: false },
  {
    id: 2,
    text: 'jako veliki text',
    dateCreated: '2022-04-08 11:22:11',
    edited: true,
  },
];

const menuItems = [{ as: 'a', content: 'Notes', key: 'note' }];

const App = () => {
  const [token, setToken] = useState();

  if (!token) {
    return <LoginPage setToken={setToken} />;
  }

  return (
    <>
      <style>{mediaStyles}</style>

      <MediaContextProvider>
        <NavBar leftItems={menuItems}>
          <AppHeader />
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/notes" element={<HomePage />} />
            </Routes>
          </BrowserRouter>
        </NavBar>
        <Footer />
      </MediaContextProvider>
    </>
  );
};

export default App;
