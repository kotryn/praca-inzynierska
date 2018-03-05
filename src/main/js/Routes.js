import React from 'react'
import { Switch, Route, Redirect } from 'react-router-dom'
import Main from './components/Main'

const Routes = () => (
    <Switch>
        <Route exact path="/" component={Main} />
        <Redirect to='/' />
    </Switch>
)

export default Routes