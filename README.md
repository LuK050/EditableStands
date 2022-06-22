# EditableStands
Плагин, добавляющий механику редактирования стоек для брони. В чём его особенность? Эта механика очень близка к ванильной, она не использует графические интерфейсы, а все делается с помощью инструментов, предметов и кликов. 
<br><br>
![a_s_aems](https://user-images.githubusercontent.com/78260779/167735238-6b73e800-fa4c-41aa-962f-05eea9cdc01a.gif)
<br>
### Использование:
**・Добавление рук:**<br>
Нажмите правой кнопкой мыши по стенду с зажатой клавишей SHIFT и 2 палками в руках.

**・Удаление рук:**<br>
Нажмите правой кнопкой мыши по стенду с зажатой клавишей SHIFT и ножницами в руках. Вам выпадет 2 палки.

**・Добавление плиты:**<br>
Нажмите правой кнопкой мыши по стенду с зажатой клавишей SHIFT и гладкой каменной плитой в руках.

**・Удаление плиты:**<br>
Нажмите правой кнопкой мыши по стенду с зажатой клавишей SHIFT и любой киркой в руках. Вам выпадет гладкая каменная плита.

**・Сделать стенд маленьким:**<br>
Нажмите правой кнопкой мыши по стенду с зажатой клавишей SHIFT и любым топором в руках. Вам выпадет 2 палки.

**・Сделать стенд большим:**<br>
Нажмите правой кнопкой мыши по стенду с зажатой клавишей SHIFT и 2 палками в руках.
<br>
### Конфигурация:
```yaml
armsAdd: true
armsRemove: true
plateAdd: true
plateRemove: true
doSmall: true
doBig: true

dropItems: true

playSounds: true

removeItems: true
removeItemsInCreative: false

damageTools: true
damageToolsInCreative: false
```

### Права:
・**editablestands.arms.add** - право на добавление рук.<br>
・**editablestands.arms.remove** - право на удаление рук.<br>
・**editablestands.plate.add** - право на добавление плиты.<br>
・**editablestands.plate.remove** - право на удаление плиты.<br>
・**editablestands.size.small** - право на уменьшение стенда.<br>
・**editablestands.size.big** - право на увеличение стенда.<br>

### Команда:
・**/editablestands reload** - перезагрузка плагина.<br>
・**/editablestands info** - информация о плагине.<br>

### Ресурсы:
・
・https://www.curseforge.com/minecraft/bukkit-plugins/editablestands
