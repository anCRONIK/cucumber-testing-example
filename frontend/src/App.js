import React from 'react';
import { NoteList } from './components/ApiResponse/NoteList';

const notes = [
  { id: 1, text: 'test', dateCreated: '2022-04-08 14:54:11', edited: false },
  {
    id: 2,
    text: 'jako veliki text',
    dateCreated: '2022-04-08 11:22:11',
    edited: true,
  },
];

function App() {
  return (
    <div className="App">
      <NoteList notes={notes} />
    </div>
  );
}

export default App;
