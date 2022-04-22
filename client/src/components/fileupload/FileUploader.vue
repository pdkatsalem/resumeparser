<template>
  <div class="parser-file-uploader">
    <div class="filepond-uploader">
      <file-pond
        name="fileUploader"
        ref="filePondUploader"
        :label-idle="label"
        :allow-file-type-validation="fileValidate"
        :accepted-file-types="acceptedTypes"
        :allow-multiple="allowMultiple"
        :data-max-file-size="maxFileSize"
        :data-max-files="maxFiles"
        :server="apiURL"
        :allow-revert="allowRevert"
        :label-file-type-not-allowed="invalidFileTypeLabel"
        :file-validate-type-label-expected-types="expectedFileTypes"
        :file-validate-type-label-expected-types-map="expectedFileTypesMap"
        v-bind:files="attachedFiles"
        v-on:processfile="processFile"
        v-on:updatefiles="updateFile">
      </file-pond>
    </div>
  </div>
</template>
<script>
import VueFilePond from 'vue-filepond'

import FilePondPluginFileValidateSize from 'filepond-plugin-file-validate-size/dist/filepond-plugin-file-validate-size.esm.js'
import FilePondPluginFileValidateType from 'filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type.esm.js'
import FilePondPluginFileMetadata from 'filepond-plugin-file-metadata/dist/filepond-plugin-file-metadata.esm.js'

import 'filepond/dist/filepond.min.css'

const FilePond = VueFilePond(FilePondPluginFileValidateType, FilePondPluginFileValidateSize, FilePondPluginFileMetadata)

export default {
  name: 'file-uploader',
  props: {
    label: {
      type: String,
      default: "Drop your files here or <span class='filepond--label-action'>Browse</span>"
    },
    allowMultiple: {
      type: Boolean,
      default: false
    },
    allowRevert: {
      type: Boolean,
      default: true
    },
    fileValidate: {
      type: Boolean,
      default: false
    },
    acceptedTypes: {
      type: Array,
      default: function () {
        return []
      }
    },
    maxFileSize: {
      type: String,
      default: '5MB'
    },
    maxFiles: {
      type: String,
      default: '3'
    },
    apiURL: {
      type: Object
    },
    attachedFiles: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  components: {
    FilePond
  },
  data: function () {
    return {
      invalidFileTypeLabel: 'Invalid File Type',
      expectedFileTypes: 'Supported file types - {allTypes}',
      expectedFileTypesMap: {
        'text/csv': '.csv',
        'text/xls': '.xls',
        'text/xlsx': '.xlsx',
        'image/jpeg': '.jpeg',
        'image/png': '.png',
        'image/jpg': '.jpg',
        'application/msword': '.doc',
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document': '.docx',
        'application/pdf': '.pdf'
      }
    }
  },
  methods: {
    processFile: function () {
      this.$emit('fileAdded', this.$refs.filePondUploader.getFiles())
    },
    updateFile: function () {
      this.$emit('fileUpdated', this.$refs.filePondUploader.getFiles())
    }
  }
}
</script>
<style lang="scss">
.filepond-uploader .filepond--root {
  font-family: inherit;
}
</style>
