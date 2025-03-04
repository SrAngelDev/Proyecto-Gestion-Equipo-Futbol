package srangeldev.repository

import srangeldev.models.Personal

interface PersonalRepository : CrudRepository<Int, Personal>  {
}