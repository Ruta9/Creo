import React, { useEffect } from 'react';
import {BrowserRouter, Switch, Route, Redirect} from 'react-router-dom';
import {connect} from 'react-redux';

import {checkIfAuthenticated} from '../actions';
import LoginForm from './frontpage/LoginForm';
import RegisterForm from './frontpage/RegisterForm';
import FrontPage from './frontpage/FrontPage';
import PageNotFoundError from './errors/PageNotFoundError';
import Header from './Header';
import ProjectsList from './projects/ProjectsList';
import ProjectPage from './project/ProjectPage';
import Loader from './common/Loader';
import ProjectSecurity from './project/ProjectSecurity';

const App = (props) => {
  
  useEffect(()=> {
      props.checkIfAuthenticated();
  }, []);

  const renderRoutes = () => {
    if (props.isAuthenticated === null) {
      return (
        <Route>
            <Loader/>
        </Route>
      );
    }
    else if (!props.isAuthenticated) {
      return (
        <Switch>
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
          <Route path="/oauth2" />
          <Route>
            <Redirect to="/login" />
          </Route>
        </Switch>
      );
    }
    else {
      return (
        <React.Fragment>
          <Header/>
          <Switch>
            <Route exact path="/projects">
              <ProjectsList/>
            </Route>
            <Route path="/project/:id">
              <ProjectSecurity>
                <ProjectPage/>
              </ProjectSecurity>
            </Route>
            <Route path={["/login", "/register", "/"]}>
              <Redirect to="/projects"/>
            </Route>
            <Route>
              <PageNotFoundError/>
            </Route>
          </Switch>
        </React.Fragment>
      );
    }
  }

  return (
    <BrowserRouter>
       {renderRoutes()}
    </BrowserRouter>
  );

}

const mapStateToProps = (state) => {
  return {
    isAuthenticated: state.isAuthenticated 
  }
}

export default connect(mapStateToProps, {checkIfAuthenticated})(App);
