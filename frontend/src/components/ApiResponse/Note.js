import React from 'react';
import { Grid } from 'semantic-ui-react';
import editedIcon from 'assets/edited.png';

export const Note = (props) => {
  const { note } = props;

  return (
    <div>
      <Grid columns={2} divided>
        <Grid.Row>
          <Grid.Column>
            <p>{note.text}</p>
          </Grid.Column>
          <Grid.Column></Grid.Column>
        </Grid.Row>
        <Grid.Row>
          <Grid.Column>{note.dateCreated}</Grid.Column>
          <Grid.Column>
            {note.edited && <img src={editedIcon} alt="edited" />}
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </div>
  );
};
