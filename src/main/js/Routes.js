import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Main from './components/Main'

const Routes = () => (
    <Switch>
        <Route key={1} exact path={'/'} render={(props) => (
            <Main {...props} url={'/usersconfig.json'} />
        )} />
        <Route key={2} exact path={'/books'} render={(props) => (
            <Main {...props} url={'/bookconfig.json'} />
        )} />
        <Route key={3} exact path={'/students'} render={(props) => (
            <Main {...props} url={'/studentconfig.json'} />
        )} />
    </Switch>
)

export default Routes