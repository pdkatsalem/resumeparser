<template>
<div class="parser-container">
  <div class="header-text-container">
      <h1>{{ headermsg }}</h1>
  </div>
  <div class="parser-data-container d-flex">
    <div class="file-upload col-md-4 offset-md-4">
      <file-uploader :allowMultiple="multiUpload"
                                :fileValidate="validateFiles"
                                :acceptedTypes="acceptedTypes"
                                :maxFileSize="maxFileSize"
                                :allowRevert="allowRevert"
                                :apiURL="apiURL"
                                v-on:fileAdded="processFileDetails"
                                v-on:fileUpdated="processFileDetails"></file-uploader>
    </div>
    <div class="file-sender col-md-2">
      <button type="button" class="btn btn-primary file-submitter" @click="parseFile">Parse</button>
    </div>
  </div>
  <div class="file-response-container">
    <file-response :response="fileResponse" :uploadmsg="fileUploadMsg"></file-response>
  </div>
</div>
</template>
<script>
import FileUpload from './fileupload/FileUploader'
import FileResponse from './FileResponse.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'document-parser',
  computed: {
    ...mapGetters([
      'currentHost'
    ])
  },
  components: {
    'file-uploader': FileUpload,
    'file-response': FileResponse
  },
  data: function () {
    return {
      headermsg: 'Welcome to Resume Parser !!!',
      apiURL: {},
      acceptedTypes: ['application/msword', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/pdf'],
      multiUpload: false,
      maxFileSize: '10MB',
      validateFiles: true,
      allowRevert: true,
      fileUploadMsg: 'Upload your document -> Click Parse -> Wait for Parsing to Complete -> Result !!!',
      fileResponse: {},
      fileDetails: {}
    }
  },
  created: function () {
    this.apiURL.url = this.currentHost + '/api/files/v1/files/113/fileupload.json'
  },
  methods: {
    setLoading: function(status) {
      this.$store.state.isLoading = status
    },
    parseFile: function () {
      this.setLoading(true)
      this.fileResponse = 'Please wait parsing file !!!'

      let fileData = {}
      fileData.serverId = this.fileDetails.serverId
      fileData.fileName = this.fileDetails.filename

      this.api.postDataParser('parser/123/parse', fileData).then((res) => {
        let status = res.data.status
        if(res.data.resumeData) {
          this.fileResponse = res.data.resumeData
        }
        this.setLoading(false)
      }, (err) => {
        console.log(err)
        this.setLoading(false)
      })
    },
    processFileDetails: function (files) {
      if(files.length) {
        this.fileDetails = files[0]
      } else {
        this.fileDetails = {}
      }
    }
  }
}
</script>
<style lang="scss" scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #35495E;
}

.header-text-container {
  margin-bottom: 2rem;
}

.parser-data-container {
  margin-top: 1rem;
  margin-bottom: 1rem;
}

.file-sender {
  align-self: center;
}
</style>
