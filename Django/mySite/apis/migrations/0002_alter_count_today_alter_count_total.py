# Generated by Django 4.2.7 on 2023-11-04 07:52

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('apis', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='count',
            name='today',
            field=models.IntegerField(default=0),
        ),
        migrations.AlterField(
            model_name='count',
            name='total',
            field=models.IntegerField(default=0),
        ),
    ]