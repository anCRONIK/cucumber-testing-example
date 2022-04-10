import { Container } from 'semantic-ui-react';
import styled from 'styled-components';

export const NavBarChildren = (props) => (
  <ChildrenContainer>{props.children}</ChildrenContainer>
);

const ChildrenContainer = styled(Container)`
  margin-top: 5em;
  min-height: 100vh;
`;
