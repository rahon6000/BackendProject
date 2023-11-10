# Generated by Django 4.2.7 on 2023-11-02 07:16

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('pistore', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='pirefdata',
            name='id_digit_2',
            field=models.ForeignKey(db_column='id_digit_2', null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='get_2', to='pistore.pidigitdata'),
        ),
        migrations.AddField(
            model_name='pirefdata',
            name='id_digit_3',
            field=models.ForeignKey(db_column='id_digit_3', null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='get_3', to='pistore.pidigitdata'),
        ),
        migrations.AddField(
            model_name='pirefdata',
            name='id_digit_4',
            field=models.ForeignKey(db_column='id_digit_4', null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='get_4', to='pistore.pidigitdata'),
        ),
        migrations.AddField(
            model_name='pirefdata',
            name='id_digit_5',
            field=models.ForeignKey(db_column='id_digit_5', null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='get_5', to='pistore.pidigitdata'),
        ),
        migrations.AddField(
            model_name='pirefdata',
            name='id_digit_6',
            field=models.ForeignKey(db_column='id_digit_6', null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='get_6', to='pistore.pidigitdata'),
        ),
        migrations.AddField(
            model_name='pirefdata',
            name='id_digit_7',
            field=models.ForeignKey(db_column='id_digit_7', null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='get_7', to='pistore.pidigitdata'),
        ),
        migrations.AddField(
            model_name='pirefdata',
            name='id_digit_8',
            field=models.ForeignKey(db_column='id_digit_8', null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='get_8', to='pistore.pidigitdata'),
        ),
        migrations.AddField(
            model_name='pirefdata',
            name='id_digit_9',
            field=models.ForeignKey(db_column='id_digit_9', null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='get_9', to='pistore.pidigitdata'),
        ),
    ]