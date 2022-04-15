import useAuth from 'hooks/useAuth';
import { React, useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Form, Grid, Segment } from 'semantic-ui-react';
import AuthenticationService from 'services/authService';
import styled from 'styled-components';

export function LoginPage() {
  const navigate = useNavigate();

  const errRef = useRef();

  const [user, setUser] = useState('');
  const [pwd, setPwd] = useState('');
  const [errMsg, setErrMsg] = useState('');

  useEffect(() => {
    //  userRef.current.focus();
  }, []);

  useEffect(() => {
    setErrMsg('');
  }, [user, pwd]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = AuthenticationService.login(user, pwd);
      console.log(JSON.stringify(response));
      setUser('');
      setPwd('');
      navigate('/notes', { replace: true });
    } catch (err) {
      if (!err?.response) {
        setErrMsg('No Server Response');
      } else if (err.response?.status === 400) {
        setErrMsg('Missing Username or Password');
      } else if (err.response?.status === 401) {
        setErrMsg('Unauthorized');
      } else {
        setErrMsg('Login Failed');
      }
      errRef.current.focus();
    }
  };

  return (
    <LoginDiv>
      <Grid.Column>
        <p
          ref={errRef}
          className={errMsg ? 'errmsg' : 'offscreen'}
          aria-live="assertive"
        >
          {errMsg}
        </p>
        <LoginHeader>Log-in to your account</LoginHeader>
        <Form size="large" onSubmit={handleSubmit}>
          <Segment stacked>
            <Form.Input
              fluid
              icon="user"
              iconPosition="left"
              placeholder="Username"
              onChange={(e) => setUser(e.target.value)}
            />
            <Form.Input
              fluid
              icon="lock"
              iconPosition="left"
              placeholder="Password"
              type="password"
              onChange={(e) => setPwd(e.target.value)}
            />

            <LoginButton>Login</LoginButton>
          </Segment>
        </Form>
      </Grid.Column>
    </LoginDiv>
  );
}

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
