import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Main from './components/Main'

const Routes = () => (
    <main>
        <Switch>
            <Route key={1} exact path={'/'} render={(props) => (
                <Main {...props} url={'/usersconfig.json'} />
            )} />/*
            <Route key={2} exact path={'/book'} render={(props) => (
                <Main {...props} url={'/bookconfig.json'} />
            )} />*/
            <Route key={3} exact path={'/book'} render={(props) => (
                <Main {...props} url={'/bookconfig.json'} />
            )} />
            <Route key={4} exact path={'/login'} render={(props) => (
                <Main {...props} url={'/loginconfig.json'} />
            )} />
        </Switch>
    </main>
)

export default Routes