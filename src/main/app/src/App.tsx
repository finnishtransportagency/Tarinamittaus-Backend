import React from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom';
import './App.css';
import urljoin from "url-join";
import routes from './App.routes';
import { v4 as uuid } from 'uuid';


function App() {
    return (
        <Router>
            <Switch>
                {routes.map((route) => (
                    <Route key={uuid()} {...route} />
                ))}
            </Switch>
            <Redirect to='/mittaus' />
        </Router>
    );
}

export default App;

const BaseRestURL =
    process.env.REACT_APP_BASE_REST_URL || "tietokatalogi/rest";

export function fullURL(...urls: string[]) {
    // NB: user with browserhistory
    // return urljoin(`/${BaseURL}`, ...urls);
    return urljoin(...urls);
}

export function fullRestURL(...urls: string[]) {
    return urljoin(`/${BaseRestURL}`, ...urls);
}
