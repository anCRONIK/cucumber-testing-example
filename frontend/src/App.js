import { Footer } from 'components/Footer/Footer';
import { AppHeader } from 'components/Header/Header';
import { NavBar } from 'containers/Navbar/Navbar';
import React from 'react';
import { MediaContextProvider, mediaStyles } from 'styles/AppMedia';

const notes = [
  { id: 1, text: 'test', dateCreated: '2022-04-08 14:54:11', edited: false },
  {
    id: 2,
    text: 'jako veliki text',
    dateCreated: '2022-04-08 11:22:11',
    edited: true,
  },
];

const leftItems = [{ as: 'a', content: 'Home', key: 'home' }];
const rightItems = [
  { as: 'a', content: 'Login', key: 'login' },
  { as: 'a', content: 'Register', key: 'register' },
];

const App = () => (
  <>
    <style>{mediaStyles}</style>

    <MediaContextProvider>
      <NavBar leftItems={leftItems} rightItems={rightItems}>
        <AppHeader />
      </NavBar>
      <Footer />
    </MediaContextProvider>
  </>
);

export default App;
