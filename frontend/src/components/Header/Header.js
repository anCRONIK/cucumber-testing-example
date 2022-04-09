import React from 'react';
import { Container } from 'semantic-ui-react';
import styled from 'styled-components';

export const AppHeader = ({ mobile }) => {
  return (
    <Container text>
      <Header mobile>Simple notes application</Header>
    </Container>
  );
};

const Header = styled.header`
  font-size: ${(props) => (props.mobile ? '3em' : '6em')};
  font-weight: 'normal';
  margin-bottom: 0;
  margin-top: ${(props) => (props.mobile ? '1.5em' : '3em')};
  text-align: center;
  background-color: palevioletred;
`;
