#!/bin/bash

# Move and rename each Java file with updated package name
for f in $(find src/main/java/com/example/marksmgmt -name "*.java"); do
    # Get the file name without the path
    filename=$(basename "$f")
    
    # Determine the subdirectory
    if [[ "$f" == *"/model/"* ]]; then
        subdir="model"
    elif [[ "$f" == *"/controller/"* ]]; then
        subdir="controller"
    elif [[ "$f" == *"/service/impl/"* ]]; then
        subdir="service/impl"
    elif [[ "$f" == *"/service/"* && ! "$f" == *"/service/impl/"* ]]; then
        subdir="service"
    elif [[ "$f" == *"/repository/"* ]]; then
        subdir="repository"
    elif [[ "$f" == *"/dto/"* ]]; then
        subdir="dto"
    elif [[ "$f" == *"/util/"* ]]; then
        subdir="util"
    else
        subdir=""
    fi
    
    # Create the target directory
    target_dir="src/main/java/com/bnpp/lynx/$subdir"
    mkdir -p "$target_dir"
    
    # Copy the file to the new location
    cp "$f" "$target_dir/"
    
    # Update the package name in the file using perl instead of sed
    if [[ -n "$subdir" ]]; then
        perl -pi -e "s/package com\.example\.marksmgmt\.$subdir/package com.bnpp.lynx.$subdir/g" "$target_dir/$filename"
    else
        perl -pi -e "s/package com\.example\.marksmgmt/package com.bnpp.lynx/g" "$target_dir/$filename"
    fi
    
    # Update import statements using perl
    perl -pi -e "s/import com\.example\.marksmgmt/import com.bnpp.lynx/g" "$target_dir/$filename"
done

# Update the main application file separately
if [ -f "src/main/java/com/example/marksmgmt/MarksManagementApplication.java" ]; then
    cp "src/main/java/com/example/marksmgmt/MarksManagementApplication.java" "src/main/java/com/bnpp/lynx/"
    perl -pi -e "s/package com\.example\.marksmgmt/package com.bnpp.lynx/g" "src/main/java/com/bnpp/lynx/MarksManagementApplication.java"
    perl -pi -e "s/import com\.example\.marksmgmt/import com.bnpp.lynx/g" "src/main/java/com/bnpp/lynx/MarksManagementApplication.java"
fi

# Update XML files
find src/main -name "*.xml" -type f -exec perl -pi -e "s/com\.example\.marksmgmt/com.bnpp.lynx/g" {} \;

# Update application.properties
if [ -f "src/main/resources/application.properties" ]; then
    perl -pi -e "s/com\.example\.marksmgmt/com.bnpp.lynx/g" "src/main/resources/application.properties"
fi

# Update spring-boot-config.xml for the main application class
if [ -f "src/main/resources/spring-boot-config.xml" ]; then
    perl -pi -e 's/value="com\.example\.marksmgmt\.MarksManagementApplication"/value="com.bnpp.lynx.MarksManagementApplication"/g' "src/main/resources/spring-boot-config.xml"
fi

echo "Package names updated successfully!" 