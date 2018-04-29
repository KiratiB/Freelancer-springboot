import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {Link,withRouter} from "react-router-dom";
import * as API from "./../api/API";
import Navbarmain from "./Navbarmain";
import  "./CSS/general.css";
import {Typeahead} from 'react-bootstrap-typeahead';
import {setProfile} from "../actions";
import 'react-bootstrap-typeahead/css/Typeahead.css';

class userProfile extends Component {

    constructor(props) {
        super(props);
        this.state = {
            disabletags: {
              firstname: true,
              lastname: true,
              email: true,
              aboutme: true,
              phonenumber: true,
              skilltag: true,
              profileFile:true,
              updatebutton : false,


            },

            userdetails: {
                userId : localStorage.getItem("userId"),
                firstname: '',
                lastname: '',
                email: '',
                aboutme: '',
                phonenumber: '',
                profileFile: '',
                profilepic : '',
                bloburl:'',
                userskills:''
            },
            skills:[]
        };
        var payload ={id:'admin@gmail.com'};
        API.fetchskills(payload)
            .then(
                (response) =>{
                    console.log(response);
                    console.log("-----------------------");
                    this.setState({
                        skills : response.skill
                    });
                }
            );





        // this.handleOptionSelected = this.handleOptionSelected.bind(this);
        this.editprofile = this.editprofile.bind(this);
        this.updateuserProfile = this.updateuserProfile.bind(this);

    };



    handleFileUpload = (event) => {

        const payload = new FormData();

        payload.append('myfile', event.target.files[0]);


        API.uploadFile(payload)
            .then((response) => {
                if (response.success) {
                    alert("Pic uploaded: Upload again to replace file");
                    this.setState({
                        userdetails: {
                            ...this.state.userdetails,
                            profileFile: "./uploads/doc/" + response.filename

                        }
                    });
                }

            });
    };

    editprofile(option){
        this.setState({
            disabletags: {
                ...this.state.disabletags,
                firstname: false,
                lastname: false,
                email:false,
                aboutme: false,
                phonenumber: false,
                profileFile: false,
                skilltag:false,
                updatebutton: true
            }
        });
    }


    updateuserProfile(option){
        this.props.dispatch(this.props.setProfile(this.state.userdetails))
    }


    componentWillReceiveProps(nextProps) {
        console.log("inside component will render");
        if (nextProps.isSetProfile === true) {
            console.log(nextProps.userDetailsS);

            // var arrayBufferView = new Uint8Array(nextProps.userDetailsS.encodeImage.data );
            // var blob = new Blob( [ arrayBufferView ], { type: "image/jpg" } );
            // var urlCreator = window.URL || window.webkitURL;
            // var imageUrl = urlCreator.createObjectURL( blob );
            // nextProps.userDetailsS.bloburl = imageUrl;
            // console.log(imageUrl);


            this.setState({
                userdetails: {
                    ...this.state.userdetails,
                    firstname: nextProps.userDetailsS.firstname,
                    lastname: nextProps.userDetailsS.lastname,
                    email: nextProps.userDetailsS.email,
                    aboutme: nextProps.userDetailsS.aboutme,
                    phonenumber: nextProps.userDetailsS.phonenumber,
                    profileFile: nextProps.userDetailsS.profileFile,
                    bloburl : nextProps.userDetailsS.bloburl,
                    userskills: nextProps.userDetailsS.userskills
                }
            });
            this.setState({
                disabletags: {
                    firstname: true,
                    lastname: true,
                    email: true,
                    aboutme: true,
                    phonenumber: true,
                    skilltag: true,
                    profileFile: true,
                    updatebutton: false,

                }
            });
        }
    }

    componentDidMount() {
        var payload ={userId: localStorage.getItem("userId")};
        API.fetchUserDetails(payload)
            .then(
                (response) =>{

                    if(response.value.length != 0) {
                        response.value.map(user => {

                            // var arrayBufferView = new Uint8Array(user.encodeImage.data);
                            // var blob = new Blob([arrayBufferView], {type: "image/jpg"});
                            // var urlCreator = window.URL || window.webkitURL;
                            // var imageUrl = urlCreator.createObjectURL(blob);
                            // user.bloburl = imageUrl;
                            // console.log(imageUrl);

                        });
                    }
                    console.log("Inside response");
                    this.setState({
                        userdetails : {
                            ...this.state.userdetails,
                            firstname : response.value[0].firstname,
                            lastname : response.value[0].lastname,
                            email : response.value[0].email,
                            aboutme : response.value[0].aboutme,
                            phonenumber : response.value[0].phonenumber,
                            profileFile : response.value[0].profileFile,
                            bloburl : response.value[0].bloburl,
                            userskills:response.value[0].userskills
                        }
                    })


                    console.log(this.state.userdetails);
                }
            );
        }

    render() {
        return (
            <div>
            <Navbarmain/>
                <div className="container-fluid">
                    <div className= "text-left float-left"><h3>User Details</h3></div>




                    <div><button className="btn btn-primary float-right" onClick={this.editprofile}>Edit Profile</button></div>
                </div>


                <div className="container-fluid panel panel-default border text-left">
                    <div className="row align-content-md-center justify-content-center">
                        <div className="align-content-md-center justify-content-center"><img className="img-rounded" src = "data:image/png;base64,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" height="130"></img></div>
                    </div>
                    <br/>
                    <div className="row align-content-md-center justify-content-center">
                        <div className="col-sm-2 font-weight-bold">Profile Pic:</div><input className="col-sm-4" disabled = {this.state.disabletags.profileFile}
                                                                                        type="file" id="file" name="file"
                                                                                        label="profilepic"
                                                                                        onChange={this.handleFileUpload}
                                                                                    />
                    </div>
                    <div className="row align-content-md-center justify-content-center">
                        <div className="col-sm-2 font-weight-bold">First Name:</div><input type="text" className="col-sm-4"  value={this.state.userdetails.firstname} disabled = {this.state.disabletags.firstname}
                                                                                           onChange={(event) => {
                                                                                               this.setState({
                                                                                                   userdetails: {
                                                                                                       ...this.state.userdetails,
                                                                                                       firstname: event.target.value
                                                                                                   }
                                                                                               });
                                                                                           }}></input>
                    </div>
                    <div className="row align-content-md-center justify-content-center">
                        <div className="col-sm-2 font-weight-bold">Last Name:</div><input type="text" className="col-sm-4"  value={this.state.userdetails.lastname} disabled = {this.state.disabletags.lastname}
                                                                                          onChange={(event) => {
                                                                                              this.setState({
                                                                                                  userdetails: {
                                                                                                      ...this.state.userdetails,
                                                                                                      lastname: event.target.value
                                                                                                  }
                                                                                              });
                                                                                          }}></input>
                    </div>
                    <div className="row align-content-md-center justify-content-center">
                        <div className="col-sm-2 font-weight-bold">Email Name:</div><input type="text" className="col-sm-4"  value={this.state.userdetails.email} disabled = {this.state.disabletags.email}
                                                                                           onChange={(event) => {
                                                                                               this.setState({
                                                                                                   userdetails: {
                                                                                                       ...this.state.userdetails,
                                                                                                       email: event.target.value
                                                                                                   }
                                                                                               });
                                                                                           }}></input>
                    </div>
                    <div className="row align-content-md-center justify-content-center">
                        <div className="col-sm-2 font-weight-bold">About Me:</div><input type="text" className="col-sm-4"  value={this.state.userdetails.aboutme} disabled = {this.state.disabletags.aboutme}
                                                                                         onChange={(event) => {
                                                                                             this.setState({
                                                                                                 userdetails: {
                                                                                                     ...this.state.userdetails,
                                                                                                     aboutme: event.target.value
                                                                                                 }
                                                                                             });
                                                                                         }}></input>
                    </div>
                    <div className="row align-content-md-center justify-content-center">
                        <div className="col-sm-2 font-weight-bold">Phone number:</div><input type="text" className="col-sm-4"  value={this.state.userdetails.phonenumber} disabled = {this.state.disabletags.phonenumber}
                                                                                             onChange={(event) => {
                                                                                                 this.setState({
                                                                                                     userdetails: {
                                                                                                         ...this.state.userdetails,
                                                                                                         phonenumber: event.target.value
                                                                                                     }
                                                                                                 });
                                                                                             }}></input>
                    </div>
                    <div className="row align-content-md-center justify-content-center">
                        <div className="col-sm-2 font-weight-bold">Skills:</div>
                        <Typeahead
                            className="col-sm-4"
                            multiple
                            labelKey="name"
                            disabled={this.state.disabletags.skilltag}
                            //selected = {this.state.userdetails.userskills}
                            options={this.state.skills}
                            placeholder="What Skills are required? "
                            onChange={this.handleOptionSelected}
                        />
                    </div>
                    <div className="col-sm-12 font-weight-bold">
                        <div className="row align-content-md-center justify-content-center">
                        { this.state.disabletags.updatebutton ? <button className="btn btn-primary btn-lg" onClick={this.updateuserProfile}>Update</button> : null }
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

const mapDispatchToProps = (dispatch)=>{
    console.log("mapDispatchToProps");
    let actions = {setProfile};
    return { ...actions, dispatch };
}


const mapStateToProps = (state) => {
    return {
        userDetailsS : state.signUpReducer.userDetails,
        isSetProfile: state.signUpReducer.isSetProfile,
    }
}
export default withRouter(connect(mapStateToProps ,mapDispatchToProps)(userProfile));