# dev docs
## !THESE DOCS ARE MEANT FOR DEVELOPERS!

# implementing into your fuel item
the current fallback for something that isint in bulk_1.json - bulk_4.json is 1 bulk\
if you want to implement bulk to your food item you need to add it to the\
[bulk_1](https://github.com/freinIsCool/adaptive-smelting/blob/master/src/main/generated/data/adaptive-smelting/tags/item/bulk_1.json), [bulk_2](https://github.com/freinIsCool/adaptive-smelting/blob/master/src/main/generated/data/adaptive-smelting/tags/item/bulk_2.json), [bulk_3](https://github.com/freinIsCool/adaptive-smelting/blob/master/src/main/generated/data/adaptive-smelting/tags/item/bulk_3.json) or [bulk_4](https://github.com/freinIsCool/adaptive-smelting/blob/master/src/main/generated/data/adaptive-smelting/tags/item/bulk_4.json) categories (tags)\
e. g\

bulk_4.json

{\
"values": [\
"MODID:crazy_fuelitem"\
]\
}