import React from 'react';
import { NoteList } from './components/ApiResponse/NoteList';

const notes = [
  { id: 1, text: 'test', dateCreted: '2022-04-08 14:54:11', edited: false },
];

function App() {
  return (
    <div className="App">
      <NoteList notes={notes} />
    </div>
  );
}

export default App;
