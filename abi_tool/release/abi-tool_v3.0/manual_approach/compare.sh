abi-compliance-checker -s -l $1 -old OLD.xml -new NEW.xml -list-affected -sysroot /home/kayes/tizen-sdk/platforms/tizen-3.0/mobile/rootstraps/mobile-3.0-device.core/ -l-full $1 -show-retval -log-path logs/$1.log