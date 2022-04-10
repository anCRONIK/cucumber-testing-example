import React from 'react';
import { Container } from 'semantic-ui-react';
import styled from 'styled-components';
import { Media } from 'styles/AppMedia';

export const AppHeader = () => {
  return (
    <HeaderContainer>
      <Media at="mobile">
        <Title mobile>Simple notes application</Title>
      </Media>
      <Media greaterThan="mobile">
        <Title>Simple notes application</Title>
      </Media>
    </HeaderContainer>
  );
};

const HeaderContainer = styled(Container)`
  margin: 0;
  padding: 0;
  height: ${(props) => (props.mobile ? '10vh' : '15vh')}; ;
`;

const Title = styled.header`
  font-size: ${(props) => (props.mobile ? '3em' : '4em')};
  font-weight: 'normal';
  margin: 0;
  padding-top: ${(props) => (props.mobile ? '1.4em' : '0.9em')};
  text-align: center;
  color: black;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
`;
