JAVAC = javac
SRC_DIR = .
BUILD_DIR = build
PACKAGE = Tree
MAIN_CLASS = $(PACKAGE).TiktokApp

SRCS = $(wildcard $(SRC_DIR)/*.java)
OBJS = $(SRCS:$(SRC_DIR)/%.java=$(BUILD_DIR)/%.class)

.PHONY: all clean

all: $(BUILD_DIR) $(BUILD_DIR)/$(MAIN_CLASS).class

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

$(BUILD_DIR)/$(PACKAGE)/%.class: $(SRC_DIR)/$(PACKAGE)/%.java
	$(JAVAC) -d $(BUILD_DIR) $<

$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) -d $(BUILD_DIR) $<

clean:
	rm -rf $(BUILD_DIR)


