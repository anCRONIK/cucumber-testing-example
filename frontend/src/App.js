import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "./App.css";

import Note from "./components/Note";
import NoteList from "./components/NoteList";

function App() {
  return (
    <Router>
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/notes" className="navbar-brand">
            Home
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/notes"} className="nav-link">
                Notes
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Add
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/notes"]} component={NoteList} />
            <Route path="/notes/:id" component={Note} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;