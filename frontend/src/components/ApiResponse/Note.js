import React from 'react';
import { Checkbox } from 'semantic-ui-react';

export const Note = (props) => {
  return (
    <div>
      <p>{props.note.text}</p>
      <span>
        <h6>{props.note.dateCreated}</h6>
        <Checkbox label="edited" checked={props.note.edited} readOnly={true} />
      </span>
    </div>
  );
};
