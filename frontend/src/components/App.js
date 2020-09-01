import React from 'react';
import {BrowserRouter, Switch, Route, Redirect} from 'react-router-dom';

import LoginForm from './frontpage/LoginForm';
import RegisterForm from './frontpage/RegisterForm';
import FrontPage from './frontpage/FrontPage';
import PageNotFoundError from './errors/PageNotFoundError';
import Header from './Header';
import ProjectsList from './projects/ProjectsList';

function App() {

  const isAuthenticated = false;
  
  return (
    <BrowserRouter>
       <div>
         {isAuthenticated ? <Header/> : null}
        <Switch>
          <Route exact path="/">
            {isAuthenticated ? <Redirect to="/projects" /> : <Redirect to="/login" />}
          </Route>
          <Route path="/login" exact>
            <FrontPage>
                <LoginForm/>
            </FrontPage>
          </Route>
          <Route path="/register" exact>
            <FrontPage>
                <RegisterForm/>
            </FrontPage>
          </Route>
          <Route path="/projects">
            <ProjectsList/>
          </Route>
          <Route>
            <PageNotFoundError/>
          </Route>
        </Switch>


       </div>
    </BrowserRouter>
  );
}

export default App;
