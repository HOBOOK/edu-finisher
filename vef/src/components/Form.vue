<template>
  <v-sheet width="300" class="mx-auto">
    <v-form validate-on="submit" @submit.prevent="submit">
      <v-text-field
        v-model="id"
        label="식별코드"
      ></v-text-field>
      
      
      <v-btn type="submit" block class="mt-2">Submit</v-btn>
    </v-form>

    {{result}}
  </v-sheet>
</template>

<script>
  export default {
    name: 'FormDefault',
    data () {
        return{
            id: '',
            timeout: null,
            pId: 'e31f75babe704036f65fc864d30e6f0e',
            sId: 'abku',
            result: null
        }
    },
    methods: {
      async submit (event) {
        const results = await event
        console.log(results)
        // let formData = new FormData()
        // formData.append('p_id', this.p_id)
        // formData.append('s_id', this.s_id)
        // formData.append('wr_order', this.wr_order)
        // formData.append('wr_page', this.wr_page)
        // formData.append('wr_id', this.id)

        // console.log(formData)
        let data = {
            'pId': this.pId,
            'sId': this.sId,
            'id': this.id,
            'order': 22
        }

        this.$axios.post('http://localhost:5000/test',data)
        .then(res=>{
            this.result = res.data.body
        })
      },
      async checkApi (userName) {
        return new Promise(resolve => {
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() => {
            if (!userName) return resolve('Please enter a user name.')
            if (userName === 'johnleider') return resolve('User name already taken. Please try another one.')

            return resolve(true)
          }, 1000)
        })
      },
    },
  }
</script>