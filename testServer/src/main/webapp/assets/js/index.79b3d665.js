(function(e){function t(t){for(var n,o,r=t[0],l=t[1],c=t[2],d=0,u=[];d<r.length;d++)o=r[d],Object.prototype.hasOwnProperty.call(s,o)&&s[o]&&u.push(s[o][0]),s[o]=0;for(n in l)Object.prototype.hasOwnProperty.call(l,n)&&(e[n]=l[n]);p&&p(t);while(u.length)u.shift()();return i.push.apply(i,c||[]),a()}function a(){for(var e,t=0;t<i.length;t++){for(var a=i[t],n=!0,r=1;r<a.length;r++){var l=a[r];0!==s[l]&&(n=!1)}n&&(i.splice(t--,1),e=o(o.s=a[0]))}return e}var n={},s={index:0},i=[];function o(t){if(n[t])return n[t].exports;var a=n[t]={i:t,l:!1,exports:{}};return e[t].call(a.exports,a,a.exports,o),a.l=!0,a.exports}o.m=e,o.c=n,o.d=function(e,t,a){o.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:a})},o.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},o.t=function(e,t){if(1&t&&(e=o(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var a=Object.create(null);if(o.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var n in e)o.d(a,n,function(t){return e[t]}.bind(null,n));return a},o.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return o.d(t,"a",t),t},o.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},o.p="/";var r=window["webpackJsonp"]=window["webpackJsonp"]||[],l=r.push.bind(r);r.push=t,r=r.slice();for(var c=0;c<r.length;c++)t(r[c]);var p=l;i.push([0,"chunk-vendors"]),a()})({0:function(e,t,a){e.exports=a("56d7")},"070c":function(e,t,a){},"0e72":function(e,t,a){},2924:function(e,t,a){},4344:function(e,t,a){"use strict";var n=a("070c"),s=a.n(n);s.a},"56d7":function(e,t,a){"use strict";a.r(t);a("e260"),a("e6cf"),a("cca6"),a("a79d");var n=a("f6b5"),s=a.n(n),i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[e._m(0),n("main",[n("img",{attrs:{src:a("e350"),alt:"Resume Cover"}}),n("router-view")],1)])},o=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("header",[a("span",[e._v("Resume Parser - Documents (.doc,.docx,.pdf)")])])}],r={name:"app",created:function(){this.$store.state.hostname=document.location.host}},l=r,c=(a("5c0b"),a("2877")),p=Object(c["a"])(l,i,o,!1,null,null,null),d=p.exports,u=a("a18c"),f=a("2f62"),m=a("0e44");s.a.use(f["a"]);var h={isLoading:!1,hostname:"",usertoken:null},v=function(e){return e.isLoading},g=function(e){return"http://"+e.hostname},y={setUserToken:function(e,t){e.usertoken=t},setLoading:function(e,t){e.isLoading=t}},b=new f["a"].Store({plugins:[Object(m["a"])({storage:window.sessionStorage})],getters:{isLoading:v,currentHost:g},state:h,mutations:y}),_=b,w=a("9483");Object(w["a"])("".concat("/","service-worker.js"),{ready:function(){console.log("App is being served from cache by a service worker.\nFor more details, visit https://goo.gl/AFskqB")},registered:function(){console.log("Service worker has been registered.")},cached:function(){console.log("Content has been cached for offline use.")},updatefound:function(){console.log("New content is downloading.")},updated:function(){console.log("New content is available; please refresh.")},offline:function(){console.log("No internet connection found. App is running in offline mode.")},error:function(e){console.error("Error during service worker registration:",e)}});a("d3b7");var x=a("bc3a"),F=a.n(x),j=document.location.host,P="http://"+j,O=P+"/api/rparse/v1/",S=F.a.create({baseURL:P,timeout:!1,params:{}});S.interceptors.request.use((function(e){return e.headers.common["Access-Control-Allow-Origin"]="*",e}),(function(e){return console.lor(e),Promise.reject(e)})),S.interceptors.response.use((function(e){return e}),(function(e){return console.log(e.config),Promise.reject(e)}));var k={postDataParser:function(e,t){console.log(t);var a="".concat(O);return a+=e+".json",S.post(a,t)}};s.a.config.productionTip=!1,u["a"].beforeEach((function(e,t,a){_.commit("setLoading",!0),a()})),u["a"].afterEach((function(e,t){_.commit("setLoading",!1)})),s.a.prototype.api=k,new s.a({router:u["a"],store:_,render:function(e){return e(d)}}).$mount("#app")},"581d":function(e,t,a){"use strict";var n=a("0e72"),s=a.n(n);s.a},"5c0b":function(e,t,a){"use strict";var n=a("9c0c"),s=a.n(n);s.a},"9c0c":function(e,t,a){},a18c:function(e,t,a){"use strict";(function(e){var n=a("f6b5"),s=a.n(n),i=a("8c4f"),o=a("b3c3");s.a.use(i["a"]);var r=[{path:"/",name:"Parser",component:o["a"]}],l=new i["a"]({mode:"history",base:e,routes:r});t["a"]=l}).call(this,"/")},b3c3:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"parser-container"},[a("div",{staticClass:"header-text-container"},[a("h1",[e._v(e._s(e.headermsg))])]),a("div",{staticClass:"parser-data-container d-flex"},[a("div",{staticClass:"file-upload col-md-4 offset-md-4"},[a("file-uploader",{attrs:{allowMultiple:e.multiUpload,fileValidate:e.validateFiles,acceptedTypes:e.acceptedTypes,maxFileSize:e.maxFileSize,allowRevert:e.allowRevert,apiURL:e.apiURL},on:{fileAdded:e.processFileDetails,fileUpdated:e.processFileDetails}})],1),a("div",{staticClass:"file-sender col-md-2"},[a("button",{staticClass:"btn btn-primary file-submitter",attrs:{type:"button"},on:{click:e.parseFile}},[e._v("Parse")])])]),a("div",{staticClass:"file-response-container"},[a("file-response",{attrs:{response:e.fileResponse,uploadmsg:e.fileUploadMsg}})],1)])},s=[],i=a("5530"),o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"parser-file-uploader"},[a("div",{staticClass:"filepond-uploader"},[a("file-pond",{ref:"filePondUploader",attrs:{name:"fileUploader","label-idle":e.label,"allow-file-type-validation":e.fileValidate,"accepted-file-types":e.acceptedTypes,"allow-multiple":e.allowMultiple,"data-max-file-size":e.maxFileSize,"data-max-files":e.maxFiles,server:e.apiURL,"allow-revert":e.allowRevert,"label-file-type-not-allowed":e.invalidFileTypeLabel,"file-validate-type-label-expected-types":e.expectedFileTypes,"file-validate-type-label-expected-types-map":e.expectedFileTypesMap,files:e.attachedFiles},on:{processfile:e.processFile,updatefiles:e.updateFile}})],1)])},r=[],l=a("1501"),c=a.n(l),p=a("14ec"),d=a("4ba6"),u=a("c723"),f=(a("4ed3"),c()(d["a"],p["a"],u["a"])),m={name:"file-uploader",props:{label:{type:String,default:"Drop your files here or <span class='filepond--label-action'>Browse</span>"},allowMultiple:{type:Boolean,default:!1},allowRevert:{type:Boolean,default:!0},fileValidate:{type:Boolean,default:!1},acceptedTypes:{type:Array,default:function(){return[]}},maxFileSize:{type:String,default:"5MB"},maxFiles:{type:String,default:"3"},apiURL:{type:Object},attachedFiles:{type:Array,default:function(){return[]}}},components:{FilePond:f},data:function(){return{invalidFileTypeLabel:"Invalid File Type",expectedFileTypes:"Supported file types - {allTypes}",expectedFileTypesMap:{"text/csv":".csv","text/xls":".xls","text/xlsx":".xlsx","image/jpeg":".jpeg","image/png":".png","image/jpg":".jpg","application/msword":".doc","application/vnd.openxmlformats-officedocument.wordprocessingml.document":".docx","application/pdf":".pdf"}}},methods:{processFile:function(){this.$emit("fileAdded",this.$refs.filePondUploader.getFiles())},updateFile:function(){this.$emit("fileUpdated",this.$refs.filePondUploader.getFiles())}}},h=m,v=(a("581d"),a("2877")),g=Object(v["a"])(h,o,r,!1,null,null,null),y=g.exports,b=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"file-response-container"},[e.isLoading?a("div",{staticClass:"loader-container"},[a("fingerprint-spinner",{attrs:{"animation-duration":1500,size:64,color:"#007bff"}})],1):a("div",{staticClass:"response-container"},[0===Object.keys(e.response).length?a("span",[e._v(e._s(e.uploadmsg))]):a("div",[e.hasKey("resumeName")?a("span",[e._v("Resume Name : "+e._s(e.response.resumeName))]):e._e(),e.hasKey("candidateName")?a("span",[e._v("Candidate Name : "+e._s(e.response.candidateName))]):e._e(),e.hasKey("emailId")?a("span",[e._v("Mail Address : "+e._s(e.response.emailId))]):e._e(),e.hasKey("phoneNumber")?a("span",[e._v("Contact Number : "+e._s(e.response.phoneNumber))]):e._e(),e.hasKey("gender")?a("span",[e._v("Gender : "+e._s(e.response.gender))]):e._e(),e.hasKey("nationality")?a("span",[e._v("Nationality : "+e._s(e.response.nationality))]):e._e(),e.hasKey("fatherName")?a("span",[e._v("Father Name : "+e._s(e.response.fatherName))]):e._e(),e.hasKey("motherName")?a("span",[e._v("Mother Name : "+e._s(e.response.motherName))]):e._e(),e.hasKey("dateOfBirth")?a("span",[e._v("Date of Birth : "+e._s(e.response.dateOfBirth))]):e._e(),e.hasKey("maritalStatus")?a("span",[e._v("Marital Status : "+e._s(e.response.maritalStatus))]):e._e(),e.hasKey("educationalDetails")?a("span",[e._v("Qualification : "+e._s(e.response.educationalDetails))]):e._e(),e.hasKey("skillSets")?a("span",[e._v("Skillset : "+e._s(e.response.skillSets))]):e._e(),e.hasKey("workProfiles")?a("span",[e._v("Work Experience : "+e._s(e.response.workProfiles))]):e._e(),e.hasKey("socialProfiles")?a("span",[e._v("Online PortFolios : "+e._s(e.response.socialProfiles))]):e._e(),e.hasKey("totalWorkExperience")?a("span",[e._v("Total Work Experience : "+e._s(e.response.totalWorkExperience))]):e._e()])])])},_=[],w=a("2f62"),x=a("4583"),F={name:"file-response",props:{response:{type:Object,default:{}},uploadmsg:{type:String,default:""}},components:{"fingerprint-spinner":x["a"]},computed:Object(i["a"])({},Object(w["b"])(["isLoading"])),data:function(){return{loadingmsg:"Please wait, Parsing File !!!"}},methods:{hasKey:function(e){return this.response.hasOwnProperty(e)}}},j=F,P=(a("4344"),Object(v["a"])(j,b,_,!1,null,"0e04d374",null)),O=P.exports,S={name:"document-parser",computed:Object(i["a"])({},Object(w["b"])(["currentHost"])),components:{"file-uploader":y,"file-response":O},data:function(){return{headermsg:"Welcome to Resume Parser !!!",apiURL:{},acceptedTypes:["application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/pdf"],multiUpload:!1,maxFileSize:"10MB",validateFiles:!0,allowRevert:!0,fileUploadMsg:"Upload your document -> Click Parse -> Wait for Parsing to Complete -> Result !!!",fileResponse:{},fileDetails:{}}},created:function(){this.apiURL.url=this.currentHost+"/api/files/v1/files/113/fileupload.json"},methods:{setLoading:function(e){this.$store.state.isLoading=e},parseFile:function(){var e=this;this.setLoading(!0),this.fileResponse="Please wait parsing file !!!";var t={};t.serverId=this.fileDetails.serverId,t.fileName=this.fileDetails.filename,this.api.postDataParser("parser/123/parse",t).then((function(t){t.data.status;t.data.resumeData&&(e.fileResponse=t.data.resumeData),e.setLoading(!1)}),(function(t){console.log(t),e.setLoading(!1)}))},processFileDetails:function(e){e.length?this.fileDetails=e[0]:this.fileDetails={}}}},k=S,L=(a("c1dd"),Object(v["a"])(k,n,s,!1,null,"6169071c",null));t["a"]=L.exports},c1dd:function(e,t,a){"use strict";var n=a("2924"),s=a.n(n);s.a},e350:function(e,t,a){e.exports=a.p+"assets/img/resume-cover.b5b8b3fd.png"}});