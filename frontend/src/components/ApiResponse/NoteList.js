import { React } from 'react';
import { Note } from './Note';

export const NoteList = (props) => {
  return (
    <div>
      {props.notes.length > 0 ? (
        props.notes.map((note) => <Note note={note} key={note.id} />)
      ) : (
        <p>No notes</p>
      )}
    </div>
  );
};
