import React, {Component} from 'react';
import './App.css';
// import HomePage from "./components/HomePage";
// import NewHomePage from "./components/NewHomePage";
import {BrowserRouter} from 'react-router-dom';
import NewerHomePage from "./components/NewerHomePage";
 import HomePage from "./components/HomePage";
 import Navbarmain from "./components/Navbarmain";
import LogoImage from "./components/LogoImage";
import Signup from "./components/Signup";


    class App extends Component {
        render() {
            return (
                <div className="App">
                    <BrowserRouter>
                        <NewerHomePage/>
                    </BrowserRouter>
                </div>
            );
        }
    }

    export default App;
