package com.example.getandpostlocation


class PersonDetails : ArrayList<PersonDetails.PersonDetailsItem>(){
    data class PersonDetailsItem(val location: String, val name: String,val pk: Int)
}