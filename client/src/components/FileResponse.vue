<template>
<div class="file-response-container">
  <div class="loader-container" v-if="isLoading">
    <fingerprint-spinner
      :animation-duration="1500"
      :size="64"
      :color="'#007bff'"/>
  </div>
  <div class="response-container" v-else>
    <span v-if="Object.keys(response).length === 0">{{ uploadmsg }}</span>
    <div v-else>
      <span v-if="hasKey('resumeName')">Resume Name : {{ response.resumeName }}</span>
      <span v-if="hasKey('candidateName')">Candidate Name : {{ response.candidateName }}</span>
      <span v-if="hasKey('emailId')">Mail Address : {{ response.emailId }}</span>
      <span v-if="hasKey('phoneNumber')">Contact Number : {{ response.phoneNumber }}</span>
      <span v-if="hasKey('gender')">Gender : {{ response.gender }}</span>
      <span v-if="hasKey('nationality')">Nationality : {{ response.nationality }}</span>
      <span v-if="hasKey('fatherName')">Father Name : {{ response.fatherName }}</span>
      <span v-if="hasKey('motherName')">Mother Name : {{ response.motherName }}</span>
      <span v-if="hasKey('dateOfBirth')">Date of Birth : {{ response.dateOfBirth }}</span>
      <span v-if="hasKey('maritalStatus')">Marital Status : {{ response.maritalStatus }}</span>
      <span v-if="hasKey('educationalDetails')">Qualification : {{ response.educationalDetails }}</span>
      <span v-if="hasKey('skillSets')">Skillset : {{ response.skillSets }}</span>
      <span v-if="hasKey('workProfiles')">Work Experience : {{ response.workProfiles }}</span>
      <span v-if="hasKey('socialProfiles')">Online PortFolios : {{ response.socialProfiles }}</span>
      <span v-if="hasKey('totalWorkExperience')">Total Work Experience : {{ response.totalWorkExperience }}</span>
    </div>
  </div>
</div>
</template>
<script>
import { mapGetters } from 'vuex'
import { FingerprintSpinner } from 'epic-spinners'

export default {
  name: 'file-response',
  props: {
    response: {
      type: Object,
      default: {}
    },
    uploadmsg: {
      type: String,
      default: ''
    }
  },
  components: {
    'fingerprint-spinner': FingerprintSpinner
  },
  computed: {
    ...mapGetters([
      'isLoading'
    ])
  },
  data: function () {
    return {
      loadingmsg: 'Please wait, Parsing File !!!'
    }
  },
  methods: {
    hasKey: function(keyname) {
      return this.response.hasOwnProperty(keyname)
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

.file-response-container {
  padding: 10px;
  margin: 2rem;
  border-radius: 10px;
  border: 1px solid gray;
  background: whitesmoke;
  text-align: justify;
}

.response-container span {
  display: block;
  margin-top: 5px;
}
</style>
