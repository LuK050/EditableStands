# EditableStands
A plugin that adds new mechanics for editing armor stands. What is its feature? This mechanic is very close to vanilla, it does not use GUI interfaces, but everything is done using tools, items and clicks. The plugin is perfect for vanilla servers!
#### ❗[ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/) is required to use the plugin
<br>

![a_s_aems](https://user-images.githubusercontent.com/78260779/167735238-6b73e800-fa4c-41aa-962f-05eea9cdc01a.gif)
<br>
### How to use:
**・Adding arms:**<br>
With SHIFT pressed, press with your hand on the stand with 2 sticks.

**・Removing arms:**<br>
With SHIFT pressed, press with your hand on the stand with shears. You will drop 2 sticks

**・Adding plate:**<br>
With SHIFT pressed, press with your hand on the stand with smooth stone slab.

**・Removing plate:**<br>
With SHIFT pressed, press with your hand on the stand with any pickaxe. You will drop smooth stone slab.

**・Make it small:**<br>
With SHIFT pressed, press with your hand on the stand with any axe. You will drop 2 sticks.

**・Make it big:**<br>
With SHIFT pressed, press with your hand on the stand with 2 sticks.
<br>
### Configuration:
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

### Permissions:
・**editablestands.arms.add** - permission to add arms.<br>
・**editablestands.arms.remove** - permission to remove arms.<br>
・**editablestands.plate.add** - permission to add plate.<br>
・**editablestands.plate.remove** - permission to remove plate.<br>
・**editablestands.size.small** - permission to downsize.<br>
・**editablestands.size.big** - permission to enlarge.<br>

### Commands:
・**/editablestands reload** - plugin reload.<br>
・**/editablestands info** - plugin information.<br>

#### ❤️ Icon by [cheharka](https://purcat.monster/)
