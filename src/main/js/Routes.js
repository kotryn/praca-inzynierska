import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Main from './components/Main'

const Routes = () => (
    <main>
        <Switch>
            <Route key={1} exact path={'/'} render={(props) => (
                <Main {...props} url={'/usersconfig.json'} />
            )} />
            <Route key={2} path={'/book'} render={(props) => (
                <Main {...props} url={'/bookconfig.json'} />
            )} />
            <Route key={3} path={'/films'} render={(props) => (
                <Main {...props} url={'/filmsconfig.json'} />
            )} />
        </Switch>
    </main>
)

export default Routes