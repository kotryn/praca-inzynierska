import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Book from './components/Book/Book'
import Users from './components/User/UsersList'

const Routes = ({config}) => (
    <main>
        <Switch>
            <Route exact path={'/'} render={(props) => (
                <Users {...props} config={config} />
            )} />
            <Route exact path='/book' component={Book}/>
        </Switch>
    </main>
)

export default Routes