import React from 'react';
import { Container, Grid, Header, List, Segment } from 'semantic-ui-react';
import styled from 'styled-components';

const FooterSegment = styled.h1`
  padding: '4em 0em';
`;

const FooterHeader = styled.h4``;

export const Footer = () => {
  return (
    <Segment inverted mini as={FooterSegment}>
      <Container>
        <Grid divided inverted stackable>
          <Grid.Row>
            <Grid.Column width={3}>
              <Header inverted as={FooterHeader} content="About" />
              <List link inverted>
                <List.Item as="a">Contact</List.Item>
              </List>
            </Grid.Column>

            <Grid.Column width={7}>
              <Header as={FooterHeader} inverted>
                anCRONIK
              </Header>
              <p>@Copyright</p>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Container>
    </Segment>
  );
};
