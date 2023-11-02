from django.db import models

# Create your models here.
class PiDigitData(models.Model):
    """ pi digit 테이블"""
    id = models.AutoField(primary_key=True, help_text="it is id and represent order of appearence")
    digit = models.IntegerField(blank=False, null=False)

class PiRefData(models.Model):
    """PiRefData reference table!"""
    id = models.AutoField(primary_key=True) # id_th n
    
    id_digit_0 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True, # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_0",
      db_column="id_digit_0")
    
    id_digit_1 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_1",
      db_column="id_digit_1")
    
    id_digit_2 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_2",
      db_column="id_digit_2")
    
    id_digit_3 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_3",
      db_column="id_digit_3")
    
    id_digit_4 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_4",
      db_column="id_digit_4")
    
    id_digit_5 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_5",
      db_column="id_digit_5")
    
    id_digit_6 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_6",
      db_column="id_digit_6")
    
    id_digit_7 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_7",
      db_column="id_digit_7")
    
    id_digit_8 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_8",
      db_column="id_digit_8")
    
    id_digit_9 = models.ForeignKey(# column itself represent digit. and FK.
      PiDigitData,  null=True,  # ref table name
      on_delete=models.SET_NULL, # ref 가 삭제될 때 행동!
      related_name="get_9",
      db_column="id_digit_9")