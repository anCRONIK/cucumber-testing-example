import React from 'react';
import { Form, Grid, Segment } from 'semantic-ui-react';
import styled from 'styled-components';
import { handleFormSubmit } from './LoginLogic';

export const LoginPage = () => (
  <LoginDiv>
    <Grid.Column>
      <LoginHeader>Log-in to your account</LoginHeader>
      <Form size="large">
        <Segment stacked>
          <Form.Input
            fluid
            icon="user"
            iconPosition="left"
            placeholder="Username"
          />
          <Form.Input
            fluid
            icon="lock"
            iconPosition="left"
            placeholder="Password"
            type="password"
          />

          <LoginButton onClick={handleFormSubmit}>Login</LoginButton>
        </Segment>
      </Form>
    </Grid.Column>
  </LoginDiv>
);

const LoginDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  text-align: center;
  vertical-align: middle;
`;

const LoginHeader = styled.h2`
  text-align: center;
  color: blue;
`;

const LoginButton = styled.button`
  background-color: #3f51b5;
  color: white;
  padding: 5px 15px;
  border-radius: 5px;
  outline: 0;
  text-transform: uppercase;
  margin: 10px 0px;
  cursor: pointer;
  box-shadow: 0px 2px 2px lightgray;
  transition: ease background-color 250ms;
  &:hover {
    background-color: #283593;
  }
  &:disabled {
    cursor: default;
    opacity: 0.7;
  }
`;
